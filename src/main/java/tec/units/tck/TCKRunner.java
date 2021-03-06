/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tec.units.tck;

import static tec.units.tck.util.TestUtils.SYS_PROPERTY_OUTPUT_DIR;
import static tec.units.tck.util.TestUtils.SYS_PROPERTY_PROFILE;
import static tec.units.tck.util.TestUtils.SYS_PROPERTY_REPORT_FILE;
import static tec.units.tck.util.TestUtils.SYS_PROPERTY_VERBOSE;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.tools.Tool;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.reporters.VerboseReporter;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import tec.units.tck.tests.FundamentalTypesTest;
import tec.units.tck.tests.format.UnitFormatTest;
import tec.units.tck.tests.quantity.QuantityInterfaceTest;
import tec.units.tck.tests.quantity.QuantityTypesTest;
import tec.units.tck.tests.spi.CreatingQuantiesTest;
import tec.units.tck.tests.unit.UnitConversionTest;
import tec.units.tck.tests.unit.UnitDimensionTest;
import tec.units.tck.tests.unit.UnitInterfaceTest;
import tec.units.tck.util.TestGroups.Group;
import tec.units.tck.util.TestGroups.Profile;
import tec.uom.lib.common.function.Versioned;

/**
 * Main class for executing the JSR 363 TCK.
 * 
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.6.1, December 28, 2015
 */
public class TCKRunner extends XmlSuite implements Tool, Versioned<String> {

    /**
     * 
     */
    private static final long serialVersionUID = 3189431432291353154L;
    private static final String TCK_VERSION = "0.7-SNAPSHOT";
    public static final String SPEC_ID = "JSR 363";
    public static final String SPEC_VERSION = "0.9.0";
    private final Profile profile;

    public TCKRunner() {
        setName(SPEC_ID + " - TCK " + TCK_VERSION);
        XmlTest test = new XmlTest(this);
        profile = Profile.valueOf(System.getProperty(SYS_PROPERTY_PROFILE, Profile.full.name()));
        for (Group group : profile.getGroups()) {
            test.addIncludedGroup(group.name());
        }
        test.setName("TCK/Test Setup");
        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass(TCKSetup.class));
        classes.add(new XmlClass(FundamentalTypesTest.class));
        classes.add(new XmlClass(UnitInterfaceTest.class));
        classes.add(new XmlClass(UnitConversionTest.class));
        classes.add(new XmlClass(UnitDimensionTest.class));
        classes.add(new XmlClass(UnitFormatTest.class));
        classes.add(new XmlClass(QuantityInterfaceTest.class));
        classes.add(new XmlClass(QuantityTypesTest.class));
        classes.add(new XmlClass(CreatingQuantiesTest.class));
        test.setXmlClasses(classes);
    }

    /**
     * Main method to start the TCK. Optional arguments are:
     * <ul>
     * <li>-Dtec.units.tck.profile for defining the profile for TestNG groups (default: full).</li>
     * <li>-Dtec.units.tck.outputDir for defining the output directory TestNG uses (default:
     * ./target/tck-output).</li>
     * <li>-Dtec.units.tck.verbose=true to enable TestNG verbose mode.</li>
     * <li>-Dtec.units.tck.reportFile=targetFile.txt for defining the TCK result summary report
     * target file (default: ./target/tck-results.txt).</li>
     * </ul>
     * 
     * @param args
     */
    @Override
    public int run(InputStream in, OutputStream out, OutputStream err, String... args) {
        System.out.println("-- " + SPEC_ID + " TCK started --");
        System.out.println("Profile: " + profile.getDescription());
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(new TCKRunner());
        final TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        String outDir = System.getProperty(SYS_PROPERTY_OUTPUT_DIR, "./target/tck-output");
        tng.setOutputDirectory(outDir);
        String verbose = System.getProperty(SYS_PROPERTY_VERBOSE);
        if ("true".equalsIgnoreCase(verbose)) {
            tng.addListener(new VerboseReporter());
        }
        String reportFile = System.getProperty(SYS_PROPERTY_REPORT_FILE, "./target/tck-results.txt");
        final File file = new File(reportFile);
        final Reporter rep = new Reporter(profile, file);
        System.out.println("Writing to file " + file.getAbsolutePath() + " ...");
        tng.addListener(rep);
        tng.run();
        rep.writeSummary();
        System.out.println("-- " + SPEC_ID + " TCK finished --");
        return 0;
    }

    @Override
    public String getVersion() {
        return TCK_VERSION;
    }

    @Override
    public final Set<SourceVersion> getSourceVersions() {
        return Collections.unmodifiableSet(new HashSet<SourceVersion>(Arrays.asList(new SourceVersion[] {SourceVersion.RELEASE_5, SourceVersion.RELEASE_6, SourceVersion.RELEASE_7})));
    }

    public static final void main(String... args) {
        if (args.length > 0 && "-version".equalsIgnoreCase(args[0])) {
            showVersion();
        } else { // (args.length > 0 && "-help".equalsIgnoreCase(args[0])) {
            showHelp();
        } /*
           * else { final Tool runner = new TCKRunner(); runner.run(System.in, System.out,
           * System.err, new String[]{TCKRunner.class.getName()}); }
           */
    }

    private static final void showHelp() {
        final StringWriter consoleWriter = new StringWriter(1000);
        consoleWriter.write("*****************************************************************************************\n");
        consoleWriter.write("**** " + SPEC_ID + " - Units of Measurement, Technical Compatibility Kit, version " + TCK_VERSION + "\n");
        consoleWriter.write("*****************************************************************************************\n\n");
        consoleWriter.write("Usage:\n");
        consoleWriter.write("To run the TCK, execute TestNG with Maven or a similar build tool.\n\n");
        consoleWriter.write("E.g. by running \"mvn test\" with this POM.\n\n");
        consoleWriter.write("You may use the following system properties to override the default behavior:\n");
        consoleWriter.write("-D" + SYS_PROPERTY_PROFILE + "=<profile>" + " to select the desired profile from these available " + SPEC_ID + " profiles:\n");
        for (Profile p : Profile.values()) {
            consoleWriter.write("   " + p.name() + " - " + p.getDescription() + (p.isDefault() ? " (the default profile)\n" : "\n"));
        }
        consoleWriter.write("-D" + SYS_PROPERTY_OUTPUT_DIR + "=<directory> to set the output directory of your choice.\n");
        consoleWriter.write("-D" + SYS_PROPERTY_REPORT_FILE + "=<file> to set the TCK result file directory of your choice.\n");
        consoleWriter.write("-D" + SYS_PROPERTY_VERBOSE + "=true/false to toggle the TCK verbose option for additional test output. The default is \"false\"\n");
        System.out.println(consoleWriter);
    }

    private static final void showVersion() {
        System.out.println(SPEC_ID + " - Units of Measurement, Technical Compatibility Kit, version \"" + TCK_VERSION + "\"\n");
    }

    public static final class Reporter extends TestListenerAdapter {

        private int count = 0;
        private int skipped = 0;
        private int failed = 0;
        private int success = 0;
        private final StringWriter consoleWriter = new StringWriter(3000);
        private FileWriter fileWriter;

        public Reporter(Profile profile, File file) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileWriter = new FileWriter(file);
                fileWriter.write("*****************************************************************************************\n");
                fileWriter.write("**** " + SPEC_ID + " - Units of Measurement, Technical Compatibility Kit, version " + TCK_VERSION + "\n");
                fileWriter.write("*****************************************************************************************\n\n");
                fileWriter.write("Executed on " + new java.util.Date() + "\n");
                fileWriter.write("Using " + profile.getDescription() + " profile\n\n");
                // System.out:
                consoleWriter.write("*****************************************************************************************\n");
                consoleWriter.write("**** " + SPEC_ID + " - Units of Measurement, Technical Compatibility Kit, version " + TCK_VERSION + "\n");
                consoleWriter.write("*****************************************************************************************\n\n");
                consoleWriter.write("Executed on " + new java.util.Date() + "\n");
                consoleWriter.write("Using " + profile.getDescription() + " profile\n\n");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        @Override
        public void onTestFailure(ITestResult tr) {
            failed++;
            count++;
            String location = tr.getTestClass().getRealClass().getSimpleName() + '#' + tr.getMethod().getMethodName();
            try {
                Method realTestMethod = tr.getMethod().getConstructorOrMethod().getMethod();
                Test testAnnot = realTestMethod.getAnnotation(Test.class);
                if (testAnnot != null && testAnnot.description() != null && !testAnnot.description().isEmpty()) {
                    if (tr.getThrowable() != null) {
                        StringWriter sw = new StringWriter();
                        PrintWriter w = new PrintWriter(sw);
                        tr.getThrowable().printStackTrace(w);
                        w.flush();
                        log("[FAILED]  " + testAnnot.description() + "(" + location + "):\n" + sw.toString());
                    } else {
                        log("[FAILED]  " + testAnnot.description() + "(" + location + ")");
                    }
                } else {
                    if (tr.getThrowable() != null) {
                        StringWriter sw = new StringWriter();
                        PrintWriter w = new PrintWriter(sw);
                        tr.getThrowable().printStackTrace(w);
                        w.flush();
                        log("[FAILED]  " + location + ":\n" + sw.toString());
                    } else {
                        log("[FAILED]  " + location);
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException("IO Error", e);
            }
        }

        @Override
        public void onTestSkipped(ITestResult tr) {
            skipped++;
            count++;
            String location = tr.getTestClass().getRealClass().getSimpleName() + '#' + tr.getMethod().getMethodName();
            try {
                Method realTestMethod = tr.getMethod().getConstructorOrMethod().getMethod();
                Test specAssert = realTestMethod.getAnnotation(Test.class);
                if (specAssert != null && specAssert.description() != null && !specAssert.description().isEmpty()) {
                    log("[SKIPPED] " + specAssert.description() + "(" + location + ")");
                } else {
                    log("[SKIPPED] " + location);
                }
            } catch (IOException e) {
                throw new IllegalStateException("IO Error", e);
            }
        }

        @Override
        public void onTestSuccess(ITestResult tr) {
            success++;
            count++;
            String location = tr.getTestClass().getRealClass().getSimpleName() + '#' + tr.getMethod().getMethodName();
            try {
                Method realTestMethod = tr.getMethod().getConstructorOrMethod().getMethod();
                Test specAssert = realTestMethod.getAnnotation(Test.class);
                if (specAssert != null && specAssert.description() != null && !specAssert.description().isEmpty()) {
                    log("[SUCCESS] " + specAssert.description() + "(" + location + ")");
                } else {
                    log("[SUCCESS] " + location);
                }
            } catch (IOException e) {
                throw new IllegalStateException("IO Error", e);
            }
        }

        private void log(String text) throws IOException {
            fileWriter.write(text);
            fileWriter.write('\n');
            consoleWriter.write(text);
            consoleWriter.write('\n');
        }

        public void writeSummary() {
            try {
                log("\n" + SPEC_ID + " TCK version " + TCK_VERSION + " Summary");
                log("-------------------------------------------------------");
                log("\nTOTAL TESTS EXECUTED : " + count);
                log("TOTAL TESTS SKIPPED  : " + skipped);
                log("TOTAL TESTS SUCCESS  : " + success);
                log("TOTAL TESTS FAILED   : " + failed);
                fileWriter.flush();
                fileWriter.close();
                consoleWriter.flush();
                System.out.println();
                System.out.println(consoleWriter);
            } catch (IOException e) {
                throw new IllegalStateException("IO Error", e);
            }
        }
    }
}

/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2014, Jean-Marie Dautelle, Werner Keil, V2COM.
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

//import tec.units.tck.tests.*;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for executing the JSR 363 TCK.
 * Created by Werner Keil on 21.12.2014.
 */
public class TCKRunner extends XmlSuite{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3189431432291353154L;

	public TCKRunner(){
        setName("JSR363-TCK 0.1");
        XmlTest test = new XmlTest(this);
        test.setName("TCK/Test Setup");
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass(TCKSetup.class));
        test.setXmlClasses(classes);
    }

    public static void main(String... args){
        System.out.println("-- JSR 363 TCK started --");
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(new TCKRunner());
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.setOutputDirectory("./target/tck-results");
//        tng.addListener(new VerboseReporter());
        File file = new File(System.getProperty("java.io.tmpdir"), "tck-results.txt");
        Reporter rep = new Reporter(file);
        System.out.println("Writing to file " + file.getAbsolutePath() + " ...");
        tng.addListener(rep);
        tng.run();
        rep.writeSummary();
        System.out.println("-- JSR 363 TCK finished --");
    }

    public static final class Reporter extends TestListenerAdapter{
        private int count = 0;
        private int skipped = 0;
        private int failed = 0;
        private int success = 0;

        private final StringWriter stringWriter = new StringWriter(3000);
        private FileWriter writer;

        public Reporter(File file){
            try{
                if(!file.exists()){
                    file.createNewFile();
                }
                writer = new FileWriter(file);
                writer.write("*****************************************************************************************\n");
                writer.write("**** JSR 363 - Units of Measurement, Technical Compatibility Kit, version 1.0\n");
                writer.write("*****************************************************************************************\n\n");
                writer.write("Executed on " + new java.util.Date() +"\n\n" );

                // System.out:
                stringWriter.write("*****************************************************************************************\n");
                stringWriter.write("**** JSR 363 - Units of Measurement, Technical Compatibility Kit, version 1.0\n");
                stringWriter.write("*****************************************************************************************\n\n");
                stringWriter.write("Executed on " + new java.util.Date() + "\n\n");
            }
            catch(IOException e){
                e.printStackTrace();
                System.exit(-1);
            }
        }

        @Override
        public void onTestFailure(ITestResult tr){
            failed++;
            String location = tr.getTestClass().getRealClass().getSimpleName()+'#'+tr.getMethod().getMethodName();
            try{
                Method realTestMethod = tr.getMethod().getMethod();
                Test testAnnot = realTestMethod.getAnnotation(Test.class);
                if(testAnnot!=null && testAnnot.description()!=null && !testAnnot.description().isEmpty()){
                    if(tr.getThrowable()!=null){
                        StringWriter sw = new StringWriter();
                        PrintWriter w = new PrintWriter(sw);
                        tr.getThrowable().printStackTrace(w);
                        w.flush();
                        log("[FAILED]  " + testAnnot.description() + "("+location+"):\n"+sw.toString());
                    }
                    else{
                        log("[FAILED]  " + testAnnot.description()+ "("+location+")");
                    }
                }
                else{

                    if(tr.getThrowable()!=null){
                        StringWriter sw = new StringWriter();
                        PrintWriter w = new PrintWriter(sw);
                        tr.getThrowable().printStackTrace(w);
                        w.flush();
                        log("[FAILED]  " + location + ":\n"+sw.toString());
                    }
                    else{
                        log("[FAILED]  " + location);
                    }
                }
            }
            catch(IOException e){
                throw new IllegalStateException("IO Error", e);
            }
        }

        @Override
        public void onTestSkipped(ITestResult tr){
            skipped++;
            String location = tr.getTestClass().getRealClass().getSimpleName()+'#'+tr.getMethod().getMethodName();
            try{
                Method realTestMethod = tr.getMethod().getMethod();
                Test specAssert = realTestMethod.getAnnotation(Test.class);
                if(specAssert!=null && specAssert.description()!=null && !specAssert.description().isEmpty()){
                    log("[SKIPPED] " + specAssert.description()+ "("+location+")");
                }
                else{
                    log("[SKIPPED] " + location);
                }
            }
            catch(IOException e){
                throw new IllegalStateException("IO Error", e);
            }
        }

        @Override
        public void onTestSuccess(ITestResult tr){
            success++;
            String location = tr.getTestClass().getRealClass().getSimpleName()+'#'+tr.getMethod().getMethodName();
            try{
                Method realTestMethod = tr.getMethod().getMethod();
                Test specAssert = realTestMethod.getAnnotation(Test.class);
                if(specAssert!=null && specAssert.description()!=null && !specAssert.description().isEmpty()){
                    log("[SUCCESS] " + specAssert.description()+ "("+location+")");
                }
                else{
                    log("[SUCCESS] " + location);
                }
            }
            catch(IOException e){
                throw new IllegalStateException("IO Error", e);
            }
        }

        private void log(String text) throws IOException {
            count++;
            writer.write(text);
            writer.write('\n');
            stringWriter.write(text);
            stringWriter.write('\n');
        }

        public void writeSummary(){
            try{
                log("\nJSR 363 TCP version 1.0 Summary");
                log("-------------------------------");
                log("\nTOTAL TESTS EXECUTED : " + count);
                log("TOTAL TESTS SKIPPED  : " + skipped);
                log("TOTAL TESTS SUCCESS  : " + success);
                log("TOTAL TESTS FAILED   : " + failed);
                writer.flush();
                writer.close();
                stringWriter.flush();
                System.out.println();
                System.out.println(stringWriter);
            }
            catch(IOException e){
                throw new IllegalStateException("IO Error", e);
            }
        }
    }
}
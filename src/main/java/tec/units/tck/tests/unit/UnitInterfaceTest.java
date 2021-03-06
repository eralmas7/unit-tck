/*
 * Unit-API - Units of Measurement API for Java Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner
 * Keil, V2COM.
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 * 
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tec.units.tck.tests.unit;

import static tec.units.tck.TCKRunner.SPEC_ID;
import static tec.units.tck.TCKRunner.SPEC_VERSION;
import javax.measure.Unit;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecVersion;
import org.testng.annotations.Test;
import tec.units.tck.TCKSetup;
import tec.units.tck.util.TestUtils;

/**
 * Testing the Unit Interface
 *
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 */
@SpecVersion(spec = SPEC_ID, version = SPEC_VERSION)
public class UnitInterfaceTest {

    /**
     * Test that Unit implementations override equals.
     */
    @SpecAssertion(section = "4.2.1", id = "421-A1")
    @Test(groups = {"core"}, description = "4.2.1 Ensure registered Unit classes override equals.")
    public void testUnitEquals() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1", type, "equals", true);
        }
    }

    /**
     * Test that Unit implementations contain getters
     */
    @SpecAssertion(section = "4.2.1", id = "421-A2")
    @Test(groups = {"core"}, description = "4.2.1 Ensure registered Unit classes implement getDimension.")
    public void testUnitGetDimension() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1", type, "getDimension");
        }
    }

    /**
     * Test that Unit implementations contain getSystemUnit
     */
    @SpecAssertion(section = "4.2.1", id = "421-A3")
    @Test(groups = {"core"}, description = "4.2.1 Ensure registered Unit classes implement getSystemUnit.")
    public void testUnitGetSystemUnit() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1", type, "getSystemUnit");
        }
    }

    /**
     * Test that Unit implementations contain getProductUnits
     */
    @SpecAssertion(section = "4.2.1", id = "421-A4")
    @Test(groups = {"core"}, description = "4.2.1 Ensure registered Unit classes implement getProductUnits.")
    public void testUnitGetProductUnits() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1", type, "getProductUnits");
        }
    }

    /**
     * Test that Unit implementations contain getters
     */
    @SuppressWarnings({"rawtypes"})
    @SpecAssertion(section = "4.2.1", id = "421-A5")
    @Test(groups = {"core"}, description = "4.2.1 Ensure registered Unit classes implement getName.")
    public void testUnitGetName() {
        for (Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1", type, "getName");
        }
    }

    /**
     * Test that Unit implementations contain getters
     */
    @SuppressWarnings({"rawtypes"})
    @SpecAssertion(section = "4.2.1", id = "421-A6")
    @Test(groups = {"core"}, description = "4.2.1 Ensure registered Unit classes implement getSymbol.")
    public void testUnitGetSymbol() {
        for (Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1", type, "getSymbol");
        }
    }

    /**
     * Test that Unit implementations override hashCode.
     */
    @SpecAssertion(section = "4.2.1", id = "421-A7")
    @Test(groups = {"core"}, description = "4.2.1 Ensure registered Unit classes override hashCode.")
    public void testUnitHashcode() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1", type, "hashCode");
        }
    }

    /**
     * Test that Unit implementations override toString.
     */
    @SpecAssertion(section = "4.2.1", id = "421-A8")
    @Test(groups = {"core"}, description = "4.2.1 Ensure registered Unit classes override toString.")
    public void testUnitToString() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1", type, "toString");
        }
    }

    /**
     * Ensure the shift() operation is implemented.
     */
    @SpecAssertion(section = "4.2.1.2", id = "42121-A1")
    @Test(groups = {"core"}, description = "4.2.1.2 Ensure the shift() operation is implemented.")
    public void testUnitShift() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1.2", type, "shift", true);
        }
    }

    /**
     * Ensure the multiply() operation is implemented.
     */
    @SpecAssertion(section = "4.2.1.3", id = "42121-A2")
    @Test(groups = {"core"}, description = "4.2.1.3 Ensure the multiply() operation is implemented.")
    public void testUnitMultiplyWithDouble() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1.3", type, Unit.class, "multiply", double.class);
        }
    }

    /**
     * Ensure the divide() operation is implemented.
     */
    @SpecAssertion(section = "4.2.1.4", id = "42121-A3")
    @Test(groups = {"core"}, description = "4.2.1.4 Ensure the divide() operation is implemented.")
    public void testUnitDivide() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1.4", type, Unit.class, "divide", Unit.class);
        }
    }

    /**
     * Ensure the multiply() operation is implemented.
     */
    @SpecAssertion(section = "4.2.1.3", id = "42121-A2")
    @Test(groups = {"core"}, description = "4.2.1.3 Ensure the multiply() operation is implemented.")
    public void testUnitMultiply() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1.3", type, Unit.class, "multiply", Unit.class);
        }
    }

    /**
     * Ensure the divide() operation is implemented.
     */
    @SpecAssertion(section = "4.2.1.4", id = "42121-A3")
    @Test(groups = {"core"}, description = "4.2.1.4 Ensure the divide() operation is implemented.")
    public void testUnitDivideWithDouble() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1.4", type, Unit.class, "divide", double.class);
        }
    }

    /**
     * Ensure the root() operation is implemented.
     */
    @SpecAssertion(section = "4.2.1.6", id = "42121-A5")
    @Test(groups = {"core"}, description = "4.2.1.6 Ensure the root() operation is implemented.")
    public void testUnitRoot() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1.6", type, "root", true);
        }
    }

    /**
     * Ensure the pow() operation is implemented.
     */
    @SpecAssertion(section = "4.2.1.7", id = "42121-A6")
    @Test(groups = {"core"}, description = "4.2.1.7 Ensure the pow() operation is implemented.")
    public void testUnitPow() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1.7", type, "pow", true);
        }
    }

    /**
     * Ensure the inverse() operation is implemented.
     */
    @SpecAssertion(section = "4.2.1.8", id = "42121-A7")
    @Test(groups = {"core"}, description = "4.2.1.8 Ensure the inverse() operation is implemented.")
    public void testUnitInverse() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.1.8", type, "inverse", false);
        }
    }

    /**
     * Ensure the getConverterTo() operation is implemented.
     */
    @SpecAssertion(section = "4.2.3", id = "423-A1")
    @Test(groups = {"core"}, description = "4.2.3 Ensure the getConverterTo() operation is implemented.")
    public void testUnitGetConverterTo() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.3", type, "getConverterTo", true);
        }
    }

    /**
     * Ensure the getConverterToAny() operation is implemented.
     */
    @SpecAssertion(section = "4.2.3", id = "423-A2")
    @Test(groups = {"core"}, description = "4.2.3 Ensure the getConverterToAny() operation is implemented.")
    public void testUnitGetConverterToAny() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.3", type, "getConverterToAny", true);
        }
    }

    /**
     * Ensure the alternate() operation is implemented.
     */
    @SpecAssertion(section = "4.2.3", id = "423-A2")
    @Test(groups = {"core"}, description = "4.2.3 Ensure the alternate() operation is implemented.")
    public void testUnitAlternate() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.3", type, "alternate", true);
        }
    }

    /**
     * Ensure the transform() operation is implemented.
     */
    @SpecAssertion(section = "4.2.3", id = "423-A3")
    @Test(groups = {"core"}, description = "4.2.3 Ensure the transform() operation is implemented.")
    public void testUnitTransform() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.3", type, "transform", true);
        }
    }

    /**
     * Ensure the isCompatible() operation is implemented.
     */
    @SpecAssertion(section = "4.2.3", id = "423-A4")
    @Test(groups = {"core"}, description = "4.2.3 Ensure the isCompatible() operation is implemented.")
    public void testUnitIsCompatible() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.3", type, "isCompatible", true);
        }
    }

    /**
     * Ensure the asType() operation is implemented.
     */
    @SpecAssertion(section = "4.2.3", id = "423-A5")
    @Test(groups = {"core"}, description = "4.2.3 Ensure the asType() operation is implemented.")
    public void testUnitAsType() {
        for (@SuppressWarnings("rawtypes")
        Class type : TCKSetup.getConfiguration().getUnitClasses()) {
            TestUtils.testHasPublicMethod("Section 4.2.3", type, "asType", true);
        }
    }
}

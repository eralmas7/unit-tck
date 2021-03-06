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
package tec.units.tck.tests.quantity;

import static tec.units.tck.TCKRunner.SPEC_ID;
import static tec.units.tck.TCKRunner.SPEC_VERSION;
import javax.measure.Quantity;
import javax.measure.Unit;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecVersion;
import org.testng.annotations.Test;
import tec.units.tck.TCKSetup;
import tec.units.tck.util.TestUtils;

/**
 * Tests for The Quantity Interface
 *
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 */
@SpecVersion(spec = SPEC_ID, version = SPEC_VERSION)
public class QuantityInterfaceTest {

    /**
     * Test that Quantity implementations override equals.
     */
    @SpecAssertion(section = "4.4.1", id = "441-A1")
    @Test(groups = {"core"}, description = "4.4.1 Ensure registered Quantity classes override equals.")
    public void testQuantityEquals() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1", type, boolean.class, "equals", Object.class);
        }
    }

    /**
     * Test that Quantity implementations override getUnit.
     */
    @SpecAssertion(section = "4.4.1", id = "441-A2")
    @Test(groups = {"core"}, description = "4.4.1 Ensure registered Quantity classes implement getUnit.")
    public void testQuantityGetUnit() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1", type, "getUnit");
        }
    }

    /**
     * Test that Quantity implementations override getValue.
     */
    @SpecAssertion(section = "4.4.1", id = "441-A3")
    @Test(groups = {"core"}, description = "4.4.1 Ensure registered Quantity classes implement getValue.")
    public void testQuantityGetValue() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1", type, "getValue");
        }
    }

    /**
     * Test that Quantity implementations override hashCode.
     */
    @SpecAssertion(section = "4.4.1", id = "441-A4")
    @Test(groups = {"core"}, description = "4.4.1 Ensure registered Quantity classes override hashCode.")
    public void testQuantityHashcode() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1", type, int.class, "hashCode");
        }
    }

    /**
     * Test that Quantity implementations override asType method.
     */
    @SpecAssertion(section = "4.4.1", id = "441-A5")
    @Test(groups = {"core"}, description = "4.4.1 Ensure registered Quantity classes implement asType method.")
    public void testQuantityCastAsType() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1.1", type, Quantity.class, "asType", Class.class);
        }
    }

    /**
     * Test that Quantity implementations override add.
     */
    @SpecAssertion(section = "4.4.1.1", id = "4411-A1")
    @Test(groups = {"core"}, description = "4.4.1.1 Ensure registered Quantity classes implement add.")
    public void testQuantityOpAdd() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1.1", type, Quantity.class, "add", Quantity.class);
        }
    }

    /**
     * Test that Quantity implementations override multiply.
     */
    @SpecAssertion(section = "4.4.1.1", id = "4411-A2")
    @Test(groups = {"core"}, description = "4.4.1.1 Ensure registered Quantity classes implement multiply.")
    public void testQuantityOpMultiply() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1.1", type, Quantity.class, "multiply", Quantity.class);
        }
    }

    /**
     * Test that Quantity implementations override divide.
     */
    @SpecAssertion(section = "4.4.1.1", id = "4411-A3")
    @Test(groups = {"core"}, description = "4.4.1.1 Ensure registered Quantity classes implement divide.")
    public void testQuantityOpDivide() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1.1", type, Quantity.class, "divide", Quantity.class);
        }
    }

    /**
     * Test that Quantity implementations override multiply with number as argument.
     */
    @SpecAssertion(section = "4.4.1.1", id = "4411-A4")
    @Test(groups = {"core"}, description = "4.4.1.1 Ensure registered Quantity classes implement multiply with number as argument.")
    public void testQuantityOpMultiplyWithNumber() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1.1", type, Quantity.class, "multiply", Number.class);
        }
    }

    /**
     * Test that Quantity implementations override divide with number as argument.
     */
    @SpecAssertion(section = "4.4.1.1", id = "4411-A5")
    @Test(groups = {"core"}, description = "4.4.1.1 Ensure registered Quantity classes implement divide with number as argument.")
    public void testQuantityOpDivideWithNumber() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1.1", type, Quantity.class, "divide", Number.class);
        }
    }

    /**
     * Test that Quantity implementations override subtract.
     */
    @SpecAssertion(section = "4.4.1.1", id = "4411-A6")
    @Test(groups = {"core"}, description = "4.4.1.1 Ensure registered Quantity classes implement subtract.")
    public void testQuantityOpSubtract() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1.1", type, Quantity.class, "subtract", Quantity.class);
        }
    }

    /**
     * Test that Quantity implementations override to method.
     */
    @SpecAssertion(section = "4.4.1.1", id = "441-A7")
    @Test(groups = {"core"}, description = "4.4.1.1 Ensure registered Quantity classes implement to method.")
    public void testQuantityConvertTo() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1.1", type, Quantity.class, "to", Unit.class);
        }
    }

    /**
     * Test that Quantity implementations override getUnit.
     */
    @SpecAssertion(section = "4.4.1.1", id = "441-A8")
    @Test(groups = {"core"}, description = "4.4.1.1 Ensure registered Quantity classes implement inverse method.")
    public void testQuantityInverse() {
        for (Class type : TCKSetup.getConfiguration().getQuantityClasses()) {
            TestUtils.testHasPublicMethod("Section 4.4.1.1", type, "inverse");
        }
    }
}

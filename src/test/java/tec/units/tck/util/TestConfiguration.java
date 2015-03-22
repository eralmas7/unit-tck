/*
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
package tec.units.tck.util;

import tec.units.tck.util.ServiceConfiguration;

import javax.measure.*;

import java.util.*;

/**
 * Created by Werner Keil on 21.12.2014.
 */
@SuppressWarnings("rawtypes")
public final class TestConfiguration implements ServiceConfiguration {

 
	@Override
    public Collection<Class> getQuantityClasses() {
            return Arrays
                    .asList(new Class[]{Quantity.class});
    }

    @Override
    public Collection<Class> getUnitClasses() {
        try{
            return Arrays
                    .asList(new Class[] { Class.forName("javax.measure.Unit")});
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("Unit class not lodable: org.javamoney.moneta.internal.JDKCurrencyAdapter");
        }
    }

    @Override
    public Collection<UnitConverter> getUnitConverters4Test(){
        List<UnitConverter> ops = new ArrayList<>();
        // TODO add unit converters
        return ops;
    }

}
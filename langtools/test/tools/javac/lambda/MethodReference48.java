/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 8003280
 * @summary Add lambda tests
 *  check that raw qualifier in unbound method reference is inferred from descriptor
 * @compile/fail/ref=MethodReference48.out -XDrawDiagnostics MethodReference48.java
 */
public class MethodReference48 {

    static class Foo<X> {
        X m() { return null; };
    }

    interface SAM1 {
        Foo<Object> m(Foo<String> fs);
    }

    interface SAM2 {
        Integer m(Foo<Integer> fi);
    }

    interface SAM3 {
        Object m(Foo<Integer> fi);
    }

    static void g1(SAM1 s) { } //return type not compatible

    static void g2(SAM2 s) { } //ok

    static void g3(SAM3 s) { } //ok

    static void g4(SAM1 s) { } //return type not compatible
    static void g4(SAM2 s) { } //ok
    static void g4(SAM3 s) { } //ok

    public static void main(String[] args) {
        g1(Foo::m);
        g2(Foo::m);
        g3(Foo::m);
        g4(Foo::m);
    }
}

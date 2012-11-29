/*
 * Sonar Erlang Plugin
 * Copyright (C) 2012 Tamas Kende
 * kende.tamas@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.erlang.checks;

import org.sonar.erlang.api.ErlangGrammar;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.squid.checks.SquidCheck;
import org.sonar.check.BelongsToProfile;
import org.sonar.check.Cardinality;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

@Rule(key = "DoNotUseImport", priority = Priority.MINOR, cardinality = Cardinality.SINGLE,
    name = "DoNotUseImport",
    description = "<p>Don't use -import, using it makes the code harder to read since " +
        "you cannot directly see in what module a function is defined. Use exref " +
        "(Cross Reference Tool) to find module dependencies.</p>")
@BelongsToProfile(title = CheckList.REPOSITORY_NAME, priority = Priority.MAJOR)
public class DoNotUseImportCheck extends SquidCheck<ErlangGrammar> {

    @Override
    public void init() {
        subscribeTo(getContext().getGrammar().importAttr);
    }

    @Override
    public void visitNode(AstNode node) {
        getContext().createLineViolation(this, "Do not use import", node);
    }

}
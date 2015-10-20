/*
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 *
 */

package org.neo4j.ogm.compiler.v2;

import java.util.Map;
import java.util.Set;

/**
 * Renders Cypher appropriate for a new node that needs creating in the database.
 *
 * @author Vince Bickers
 */
class NewNodeBuilder extends NodeBuilder {

    NewNodeBuilder(String variableName) {
        super(variableName);
    }

    @Override
    public boolean emit(StringBuilder queryBuilder, Map<String, Object> parameters, Set<String> varStack) {

        queryBuilder.append('(');
        queryBuilder.append(this.reference());
        for (String label : this.labels) {
            queryBuilder.append(":`").append(label).append('`');
        }
        if (!this.props.isEmpty()) { //TODO this syntax will be deprecated in Neo4j 2.3
            queryBuilder.append('{').append(this.reference()).append("_props}");
            parameters.put(this.reference() + "_props", this.props);
        }
        queryBuilder.append(')');
        varStack.add(this.reference());

        return true;
    }

}
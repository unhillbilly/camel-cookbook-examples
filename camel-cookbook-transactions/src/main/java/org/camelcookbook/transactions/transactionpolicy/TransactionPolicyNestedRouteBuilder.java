/*
 * Copyright (C) Scott Cranton and Jakub Korab
 * https://github.com/CamelCookbook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camelcookbook.transactions.transactionpolicy;

import org.apache.camel.builder.RouteBuilder;

/**
 * Demonstrates the use of nested transaction policies.
 */
public class TransactionPolicyNestedRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:policies")
            .setHeader("message", simple("${body}"))
            .policy("PROPAGATION_REQUIRED")
                .to("sql:insert into messages (message) values (:#message)")
                .to("direct:nestedPolicy")
                .to("mock:out1")
            .end();

        from("direct:nestedPolicy")
            .policy("PROPAGATION_NOT_SUPPORTED")
                .to("sql:insert into audit_log (message) values (:#message)")
                .to("mock:out2")
            .end();
    }
}

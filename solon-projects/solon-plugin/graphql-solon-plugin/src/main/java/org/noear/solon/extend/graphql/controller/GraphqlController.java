package org.noear.solon.extend.graphql.controller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.core.handle.Context;
import org.noear.solon.extend.graphql.execution.GraphQlSource;
import org.noear.solon.extend.graphql.support.GraphqlRequestParam;

/**
 * @author fuzi1996
 * @since 2.3
 */
@Controller
public class GraphqlController {

    @Inject
    private GraphQlSource graphQlSource;

    @Post
    @Mapping("/graphql")
    public Object graphql(GraphqlRequestParam param, Context request) {
        GraphQL graphQL = this.graphQlSource.graphQl();
        ExecutionResult executionResult = graphQL.execute(param.getQuery());
        return executionResult.getData();
    }

    @Post
    @Mapping("/schema")
    public Object getSchema(Object data, Context request) {
        GraphQL graphQL = this.graphQlSource.graphQl();
        ExecutionResult executionResult = graphQL.execute("{hello}");

        return executionResult.getData();
    }

    public static final String getRemoteId(Context ctx) {
        return ctx.realIp() + ctx.userAgent();
    }
}

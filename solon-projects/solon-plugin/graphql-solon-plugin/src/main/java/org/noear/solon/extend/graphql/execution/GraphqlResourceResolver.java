package org.noear.solon.extend.graphql.execution;

import java.util.Set;
import org.noear.solon.extend.graphql.support.Resource;

/**
 * @author fuzi1996
 * @since 2.3
 */
public interface GraphqlResourceResolver {

    default boolean isNeedAppend(Set<Resource> existsResource) {
        return true;
    }

    Set<Resource> getGraphqlResource();
}

package org.jenkinsci.plugins.systemconfigdsl;

import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.InvokerHelper;

/**
 * @author Kohsuke Kawaguchi
 */
public class Surrogate extends PropertyBuilder {
    private MetaClass metaClass;
    private final Object target;

    public Surrogate(Object target) {
        super(target.getClass());
        this.target = target;
    }

    @Override
    public MetaClass getMetaClass() {
        if (metaClass == null) {
            metaClass = InvokerHelper.getMetaClass(target.getClass());
        }
        return metaClass;
    }

    /**
     * Set all the accumulated properties into the target object.
     */
    public void assign() {
        handleSetters(target);
    }
}
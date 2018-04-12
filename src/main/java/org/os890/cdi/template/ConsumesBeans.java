package org.os890.cdi.template;

import javax.inject.Inject;

public class ConsumesBeans
{
    @Inject
    private ApplicationScopedBean applicationScopedBean;

    @Inject
    private DependentBean dependantBean;

    public Integer multiply()
    {
	return this.applicationScopedBean.getValue() * this.dependantBean.getValue();
    }
}

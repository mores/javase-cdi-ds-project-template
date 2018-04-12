/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.os890.cdi.test;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.apache.deltaspike.testcontrol.api.mock.DynamicMockManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.os890.cdi.template.ApplicationScopedBean;
import org.os890.cdi.template.ConsumesBeans;
import org.os890.cdi.template.DependentBean;

import javax.inject.Inject;

import static org.mockito.Mockito.*;

@RunWith(CdiTestRunner.class)
public class SimpleMockTest
{
    @Inject
    private ConsumesBeans consumesBeans;

    @Inject
    private ApplicationScopedBean applicationScopedBean;

    @Inject
    private DependentBean dependentBean;

    @Inject
    private DynamicMockManager mockManager;

    @Test
    public void injectionTest()
    {
        applicationScopedBean = mock(ApplicationScopedBean.class);
        when(applicationScopedBean.getValue()).thenReturn(7);
        mockManager.addMock(applicationScopedBean);

        Assert.assertEquals(new Integer(7), this.applicationScopedBean.getValue());

        dependentBean = mock(DependentBean.class);
        when(dependentBean.getValue()).thenReturn(3);
        mockManager.addMock(dependentBean);

        Assert.assertEquals(new Integer(3), this.dependentBean.getValue());

        Assert.assertEquals(new Integer(21), this.consumesBeans.multiply());
    }
}

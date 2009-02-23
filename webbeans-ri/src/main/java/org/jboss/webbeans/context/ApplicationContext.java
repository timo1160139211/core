/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.webbeans.context;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.context.ApplicationScoped;

import org.jboss.webbeans.context.api.BeanStore;

/**
 * The Application context
 * 
 * @author Nicklas Karlsson
 * 
 * @see org.jboss.webbeans.context.ApplicationContext
 */
public class ApplicationContext extends AbstractMapContext
{

   public static ApplicationContext INSTANCE;
   
   public static ApplicationContext create()
   {
      INSTANCE = new ApplicationContext();
      return INSTANCE;
   }

   // The beans
   private BeanStore beanStore;
   // Is the context active?
   private AtomicBoolean active;

   /**
    * Constructor
    */
   protected ApplicationContext()
   {
      super(ApplicationScoped.class);
      this.active = new AtomicBoolean(false);
   }

   /**
    * Gets the bean map
    * 
    * @return The bean map
    */
   @Override
   public BeanStore getBeanStorage()
   {
      return this.beanStore;
   }

   /**
    * Sets the bean map
    * 
    * @param applicationBeanStore The bean map
    */
   public void setBeanStore(BeanStore applicationBeanStore)
   {
      this.beanStore = applicationBeanStore;
   }

   /**
    * Indicates if the context is active
    * 
    * @return True if active, false otherwise
    */
   @Override
   public boolean isActive()
   {
      return active.get();
   }

   /**
    * Sets the active state of the context
    * 
    * @param active The new state
    */
   @Override
   public void setActive(boolean active)
   {
      this.active.set(active);
   }

   @Override
   public String toString()
   {
      String active = isActive() ? "Active " : "Inactive ";
      String beanStoreInfo = getBeanStorage() == null ? "" : getBeanStorage().toString();
      return active + "application context " + beanStoreInfo;
   }

}

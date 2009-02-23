package org.jboss.webbeans.context;

import java.lang.annotation.Annotation;

import org.jboss.webbeans.context.api.BeanStore;

public abstract class AbstractThreadLocalMapContext extends AbstractMapContext
{
   
   private final ThreadLocal<BeanStore> beanStore;

   public AbstractThreadLocalMapContext(Class<? extends Annotation> scopeType)
   {
      super(scopeType);
      this.beanStore = new ThreadLocal<BeanStore>();
   }

   /**
    * Gets the bean map
    * 
    * @returns The bean map
    */
   @Override
   public BeanStore getBeanStorage()
   {
      return beanStore.get();
   }

   /**
    * Sets the bean map
    * 
    * @param beanStore The bean map
    */
   public void setBeanStore(BeanStore beanStore)
   {
      this.beanStore.set(beanStore);
   }
   
}
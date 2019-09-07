package com.yt.delegate.mvc;

import com.yt.delegate.controller.OrderAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟springmvc servletDispatcher
 * （相当于项目经理 boss为客户输入的url；无需频繁的配置wen.xml中的servlet）
 */
public class ServletDispatcher {

    //维护映射关系 可以理解为策略常量
    List<Handler> hadnlerMapping = new ArrayList<Handler>();

    public ServletDispatcher() {
        Class<OrderAction> orderActionClass = OrderAction.class;
        try {
            hadnlerMapping.add(new Handler().setController(orderActionClass.newInstance())
            .setMetheod(orderActionClass.getMethod("geOrderById", String.class))
            .setUrl("/order/getOrderById"));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void doService(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        doDispatch(request, response);
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //1.获取用户请求的url
        String uri = request.getRequestURI();

        //2.servlet拿到url后作出选择 通过url找到对应的某一个java类
        //3.通过拿到的Url去handlerMapping（策略常量）
        Handler hd = null;
        for (Handler handler : hadnlerMapping) {
            if (uri.equals(handler.getUrl())) {
                hd = handler;
            }
        }

        //4.将具体的任务分发给method
        Object method = hd.getMetheod().invoke(hd.getController(), request.getParameter("orderId"));

        //5.获取method执行的结果，通过response返回。
    //    response.getWriter().write();
    }


    class Handler{
        private Object controller;
        private Method metheod;
        private String url;

        public Object getController() {
            return controller;
        }

        //为了赋值方便
        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMetheod() {
            return metheod;
        }

        public Handler setMetheod(Method metheod) {
            this.metheod = metheod;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }
    }
}

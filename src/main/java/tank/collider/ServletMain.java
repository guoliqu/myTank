package tank.collider;

import java.util.ArrayList;
import java.util.List;

public class ServletMain {

    public static void main(String[] args) {
        Request request = new Request();
        request.str = "requst";
        Response response = new Response();
        response.str = "response";
        FilterChain filterChain = new FilterChain();
        filterChain.add(new HTMLFilter());
        filterChain.add(new SensitiveFilter());
        filterChain.doFilter(request, response, filterChain);

        System.out.println(request.str);
        System.out.println(response.str);
    }
}

interface MyServletFilter{
    void doFilter(Request request, Response response, FilterChain chain);
}

class Request {
    String str;
}

class Response {
    String str;
}

class FilterChain implements MyServletFilter{
    List<MyServletFilter> list = new ArrayList<>(8);
    int index = 0;

    public FilterChain add(MyServletFilter myServletFilter){
        list.add(myServletFilter);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain){
        if (index == list.size()) return;
        MyServletFilter f = list.get(index);
        index++;
        f.doFilter(request, response, chain);
    }
}

class SensitiveFilter implements MyServletFilter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str += "  SensitiveFilter start  ";
        chain.doFilter(request, response, chain);
        response.str += "  SensitiveFilter end  ";
    }
}

class HTMLFilter implements MyServletFilter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str += "  HTMLFilter start  ";
        chain.doFilter(request, response, chain);
        response.str += "  HTMLFilter end  ";
    }
}
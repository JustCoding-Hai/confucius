package top.javahai.confucius.service.gateway.filter;


import com.google.gson.JsonObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.javahai.confucius.frame.common.helper.JwtHelper;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description: 全局鉴权过滤器
 * @create 2021/1/31 - 10:14
 **/
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static final String AUTH_CHECK_PATH="/api/**/auth/**";
    private static final String LOGIN_REQUEST="请您先登录再访问！";
    private static final String SESSION_EXPIRED="当前会话已过期，请重新登录！";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if (antPathMatcher.match(AUTH_CHECK_PATH,path)){
            List<String> tokenList = request.getHeaders().get("token");
            //没有token
            if (tokenList==null){
                ServerHttpResponse response = exchange.getResponse();
                return out(response,LOGIN_REQUEST);
            }
            //token校验失败
            boolean isCorrectToken = JwtHelper.checkJwtTToken(tokenList.get(0));
            if (!isCorrectToken){
                ServerHttpResponse response = exchange.getResponse();
                return out(response,SESSION_EXPIRED);
            }

        }
        //放行
        return chain.filter(exchange);
    }

    /**
     * 定义过滤器的优先级，值越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }


    private Mono<Void> out(ServerHttpResponse response,String prompt) {
        JsonObject message = new JsonObject();
        message.addProperty("success", false);
        message.addProperty("code", 28004);
        message.addProperty("data", "");
        message.addProperty("message", prompt);
        byte[] bytes = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        //输出http响应
        return response.writeWith(Mono.just(buffer));
    }
}

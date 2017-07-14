package com.mapbar.display.filter;

/*
public class AccessFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 0;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getHeader("accessToken");
        User user = new User();
        user.setToken(accessToken.toString());
        String re = restTemplate.postForObject("http://127.0.0.1:22222/validation",user,String.class);
        if("FAIL".equals(re)) {
            log.warn("access token is invalid");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("sorry,access token is invalid");
            return null;
        }
        log.info("access token ok");
        return null;
    }
}*/
public class AccessFilter{}
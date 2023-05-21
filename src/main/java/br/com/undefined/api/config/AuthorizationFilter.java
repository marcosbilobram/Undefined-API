package br.com.undefined.api.config;


import org.springframework.stereotype.Component;

@Component
public class AuthorizationFilter /*extends OncePerRequestFilter*/ {

    //@Autowired
    //private TokenService tokenService;

    /*@SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = getToken(request);

        if (token != null){
            var usuario = tokenService.getValidateUser(token);
            Authentication auth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization"); // Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")){
            return null;
        }

        return header.replace("Bearer ", "");
    }*/

}
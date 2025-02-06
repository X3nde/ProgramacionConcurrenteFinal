package com.progcon.programacionconcurrentefinal.security_monitoring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    /**
     * Intercepta la ejecución de cualquier método dentro del paquete "service"
     * para realizar comprobaciones de seguridad.
     */
    @Before("execution(* com.progcon.programacionconcurrentefinal.security_monitoring.service.*.*(..))")
    public void checkSecurity(JoinPoint joinPoint) {
        // Aquí se puede implementar la lógica de verificación (roles, permisos, etc.)
        System.out.println("Verificando seguridad antes de ejecutar: "
                + joinPoint.getSignature().getName());
    }
}

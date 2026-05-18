import { auth } from "express-oauth2-jwt-bearer";

// Este middleware validará el token JWT en el header 'Authorization: Bearer <token>'
export const validarToken = auth({
    audience: process.env.AUTH0_AUDIENCE, // ej. 'https://api.inspecciones.com'
    issuerBaseURL: process.env.AUTH0_ISSUER_URL, // ej. 'https://dev-tu-tenant.us.auth0.com/'
    tokenSigningAlg: 'RS256'
});
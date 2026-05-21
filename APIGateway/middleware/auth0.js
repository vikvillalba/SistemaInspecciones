import { auth } from "express-oauth2-jwt-bearer";

// Este middleware validará el token JWT en el header 'Authorization: Bearer <token>'
export const validarToken = auth({
    audience: process.env.AUTH0_AUDIENCE, 
    issuerBaseURL: process.env.AUTH0_ISSUER_URL,
    tokenSigningAlg: 'RS256'
});
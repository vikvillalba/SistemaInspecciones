//Traducción para que el frontend reciba códigos HTTP correctos.
const grpcToHttp = {
    NOT_FOUND: 404,
    INVALID_ARGUMENT: 400,
    ALREADY_EXISTS: 409,
    PERMISSION_DENIED: 403,
    UNAUTHENTICATED: 401,
    INTERNAL: 500,
    UNAVAILABLE: 503,
    UNIMPLEMENTED: 501,
};

export default function errorHandler(err, req, res, _next) {
    console.error(`[ERROR] ${req.method} ${req.path}:`, err.message);
    const status = grpcToHttp[err.code] ?? 500;
    res.status(status).json({
        error: err.details || err.message || "Error interno del servidor",
    });
}
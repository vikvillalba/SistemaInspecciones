import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { Auth0Provider } from '@auth0/auth0-react';


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Auth0Provider
      domain="dev-p00wdpmne85hpxgo.us.auth0.com"
      clientId="oQf0tWJeLyKG7756QiCcvGKhhMioOGnL"
      authorizationParams={{
        redirect_uri: window.location.origin,
        audience: "https://api.inspecciones.com",
        scope: "openid profile email read:productos read:defectos write:inspecciones"

      }}
    >
      <App />
    </Auth0Provider>
  </StrictMode>,
)

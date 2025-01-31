import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import {
  createBrowserRouter,
  Navigate,
  RouterProvider,
} from "react-router-dom";
import { AuthProvider, useAuth } from "./context/authContext";
import HomePage from "./views/pages/HomePage";
import LoginPage from "./views/pages/LoginPage";
import "./index.css";

// eslint-disable-next-line react-refresh/only-export-components, react/prop-types
const AuthGuard = ({ children }) => {
  const { isAuthenticated } = useAuth();
  return isAuthenticated ? children : <Navigate to="/login" />;
};

// eslint-disable-next-line react-refresh/only-export-components, react/prop-types
const LoginGuard = ({ children }) => {
  const { isAuthenticated } = useAuth();
  return isAuthenticated ? <Navigate to="/" /> : children;
};

const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <AuthGuard>
        <HomePage />
      </AuthGuard>
    ),
  },
  {
    path: "/login",
    element: (
      <LoginGuard>
        <LoginPage />
      </LoginGuard>
    ),
  },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </StrictMode>
);

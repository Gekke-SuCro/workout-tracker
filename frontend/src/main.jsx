import { StrictMode, useMemo } from "react";
import { createRoot } from "react-dom/client";
import {
  createBrowserRouter,
  Navigate,
  RouterProvider,
} from "react-router-dom";
import "./index.css";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";

// eslint-disable-next-line react-refresh/only-export-components, react/prop-types
const AuthGuard = ({ children }) => {
  const isAuthenticated = useMemo(() => !!localStorage.getItem("token"), []);
  return isAuthenticated ? children : <Navigate to="/login" />;
};

// eslint-disable-next-line react-refresh/only-export-components, react/prop-types
const LoginGuard = ({ children }) => {
  const isAuthenticated = useMemo(() => !!localStorage.getItem("token"), []);
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
    <RouterProvider router={router} />
  </StrictMode>
);

import { createContext, useContext, useState } from "react";
import AuthService from "../services/authService";

const AuthContext = createContext(undefined);

// eslint-disable-next-line react/prop-types
export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(
    AuthService.isAuthenticated()
  );

  const login = async (username, password) => {
    const succes = await AuthService.login(username, password);
    if (succes) {
      setIsAuthenticated(true);
    }
    return succes;
  };

  const registerNewUser = async (username, password, confirmPassword) => {
    return await AuthService.register(username, password, confirmPassword);
  };

  const logout = () => {
    AuthService.logout();
    setIsAuthenticated(false);
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, login, logout, registerNewUser }}
    >
      {children}
    </AuthContext.Provider>
  );
};

// eslint-disable-next-line react-refresh/only-export-components
export const useAuth = () => {
  return useContext(AuthContext);
};

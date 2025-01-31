import { AuthAPI } from "../api/authApi";

const TOKEN_KEY = "token";

const storeToken = (token) => {
  localStorage.setItem(TOKEN_KEY, token);
};

const clearToken = () => {
  localStorage.removeItem(TOKEN_KEY);
};

const getToken = () => {
  return localStorage.getItem(TOKEN_KEY);
};

const AuthService = {
  login: async function (username, password) {
    try {
      const { accesToken } = await AuthAPI.login(username, password);
      storeToken(accesToken);

      return accesToken;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  logout: function () {
    clearToken();
  },

  isAuthenticated: function () {
    return !!getToken();
  },

  getToken: function () {
    return getToken();
  },
};

export default AuthService;

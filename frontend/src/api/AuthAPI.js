import api from "./configs/apiConfig";
import {getErrorMessage} from "./configs/apiGlobals.js";

export const AuthAPI = {
  login: async function (username, password) {
    try {
      const response = await api.post("/auth/login", { username, password });
      return response.data;
    } catch (error) {
        throw new Error(getErrorMessage(error));
    }
  },

  register: async function (username, password, confirmPassword) {
    try {
      const response = await api.post("/auth/register", {
        username: username,
        password: password,
        confirmPassword: confirmPassword,
      });

      return response.data;
    } catch (error) {
      throw new Error(getErrorMessage(error));
    }
  },
};

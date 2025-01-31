import api from "./configs/apiConfig";

export const AuthAPI = {
  login: async function (username, password) {
    try {
      const response = await api.post("/auth/login", { username, password });

      return response.data;
    } catch (error) {
      if (error.response) {
        const status = error.response.status;
        if (status === 401) {
          throw new Error("Invalid credentials. Please try again.");
        } else if (status === 500) {
          throw new Error("Server error. Please try later.");
        }
      }
      throw new Error("Login failed. Please try again.");
    }
  },
};

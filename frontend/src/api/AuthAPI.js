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

  register: async function (firstname, lastname, username, password) {
    try {
      const response = await api.post("/auth/register", {
        firstname: firstname,
        lastname: lastname,
        username: username,
        password: password,
      });
      return response.data;
    } catch (error) {
      console.log(error);
      console.log(error.response);
      const status = error.response.status;
      if (status === 500) {
        throw new Error("Server error. Please try later.");
      } else {
        if (status === 403) {
          throw new Error("Username already taken!");
        }
      }
      throw new Error("Login failed. Please try again.");
    }
  },
};

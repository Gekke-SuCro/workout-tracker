import api from "./configs/apiConfig";

export const AuthAPI = {
  login: async function (username, password) {
    try {
      const response = await api.post("/auth/login", { username, password });
      return response.data;
    } catch (error) {
        throw new Error(getErrorMessage(error));
    }
  },

  register: async function (firstname, lastname, username, password, confirmPassword) {
    try {
      const response = await api.post("/auth/register", {
        firstname: firstname,
        lastname: lastname,
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

function getErrorMessage(error) {
  if (error.response) {
    return error.response.data || "An error occurred.";
  } else if (error.request) {
    return "Could not connect to server.";
  } else {
    return "An unexpected error occurred.";
  }
}
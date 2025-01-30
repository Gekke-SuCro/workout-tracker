import api from "./configs/apiConfig";

export const AuthAPI = {
  login: async function (username, password) {
    try {
      const response = await api.post("/auth/login", {
        username: username,
        password: password,
      });

      //   Store token in localstorage (for now)
      console.log(response);
      localStorage.setItem("token", response.data.accesToken);

      return response.data;
    } catch (error) {
      throw error.response?.data?.message || "Login failed, try again!";
    }
  },
};

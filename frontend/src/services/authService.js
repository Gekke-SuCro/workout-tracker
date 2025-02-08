import {AuthAPI} from "../api/authApi";

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
            const response = await AuthAPI.login(username, password);
            storeToken(response.token);
            return response.username;
        } catch (error) {
            throw new Error(error.message);
        }
    },

    register: async function (username, password, confirmPassword) {
        try {
            const authResponse = await AuthAPI.register(
                username,
                password,
                confirmPassword
            );
            return authResponse.data;
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
};

export default AuthService;

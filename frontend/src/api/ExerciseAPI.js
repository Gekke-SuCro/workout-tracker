import api from "./configs/apiConfig";
import {getErrorMessage} from "./configs/apiGlobals.js";

export const ExerciseAPI = {
  getAll: async function () {
    try {
      const response = await api.get("/exercises");
      return response.data;
    } catch (error) {
        throw new Error(getErrorMessage(error));
    }
  },
};

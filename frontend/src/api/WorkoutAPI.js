import api from "./configs/apiConfig";
import {getErrorMessage} from "./configs/apiGlobals.js";

export const WorkoutAPI = {
  create: async function (name, date, exercises) {
    console.log({
      name: name,
      date: date,
      exercises: exercises,
    });
    try {
      const response = await api.post("/workouts", {
        name: name,
        date: date,
        exercises: exercises,
      });
      console.log(response);
      return response.data;
    } catch (error) {
        throw new Error(getErrorMessage(error));
    }
  },
};

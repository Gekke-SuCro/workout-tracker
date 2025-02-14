import api from "./configs/apiConfig";

export const WorkoutAPI = {
  create: async function (name, date, exercises) {
    try {
      const response = await api.post("/workouts", {
        name: name,
        date: date,
        exercises: exercises,
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
export function getErrorMessage(error) {
    if (error.response) {
        return error.response.data || "An error occurred.";
    } else if (error.request) {
        return "Could not connect to server.";
    } else {
        return "An unexpected error occurred.";
    }
}
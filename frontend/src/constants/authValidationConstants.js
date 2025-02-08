export const USERNAME_MIN_LENGTH = 3;
export const USERNAME_MAX_LENGTH = 30;
export const USERNAME_LENGTH_MESSAGE = `Username must be between ${USERNAME_MIN_LENGTH} and ${USERNAME_MAX_LENGTH} characters.`;
export const USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9]*$";
export const USERNAME_REGEX_MESSAGE = "Username must start with a letter and contain only letters and/or numbers.";

export const PASSWORD_MIN_LENGTH = 8;
export const PASSWORD_MAX_LENGTH = 128;
export const PASSWORD_LENGTH_MESSAGE = `Password must be between ${PASSWORD_MIN_LENGTH} and ${PASSWORD_MAX_LENGTH} characters.`;
export const PASSWORD_REGEX = "^\\S+$";
export const PASSWORD_REGEX_MESSAGE = "Password cannot contain whitespaces.";

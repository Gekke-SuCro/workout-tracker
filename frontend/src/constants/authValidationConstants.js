
// Name validation rules
export const NAME_MIN = 2;
export const NAME_MAX = 50;
export const NAME_REGEX = "^[A-Za-z]+$";

// Username validation rules
export const USERNAME_MIN = 3;
export const USERNAME_MAX = 30;
export const USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9]*$";

// Password validation rules
export const PASSWORD_MIN = 8;
export const PASSWORD_MAX = 128;
export const PASSWORD_REQUIRED = "Password is required";
export const PASSWORD_REGEX = "^(?!.*\\s).*$";
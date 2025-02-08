import {useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {ToastContainer, toast} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "../../assets/styles/formStyles.css";
import {useAuth} from "../../context/authContext";
import {
    NAME_MAX,
    NAME_MIN,
    NAME_REGEX, PASSWORD_MAX, PASSWORD_MIN, PASSWORD_REGEX, PASSWORD_REQUIRED,
    USERNAME_MAX,
    USERNAME_MIN,
    USERNAME_REGEX
} from "../../constants/authValidationConstants.js";

const RegisterPage = () => {
    const {registerNewUser} = useAuth();
    const {
        register,
        handleSubmit,
        formState: {errors},
        watch
    } = useForm({mode: "onBlur"});

    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const onSubmit = async (data) => {
        setLoading(true);
        const authToast = toast.loading("Creating account...");

        try {
            await registerNewUser(
                data.firstname,
                data.lastname,
                data.username,
                data.password,
                data.confirmPassword,
            );

            toast.update(authToast, {
                render: "Account created, you can now login! 🎉",
                type: "success",
                isLoading: false,
                closeButton: true,
                autoClose: 3000,
            });

            await new Promise((resolve) => setTimeout(resolve, 1000));
            navigate("/login");
        } catch (error) {
            console.error(error.message);
            toast.update(authToast, {
                render: error.message,
                type: "error",
                isLoading: false,
                closeButton: true,
                autoClose: 3000,
            });
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="mx-auto sm:max-w-md flex flex-col grow justify-center min-h-svh">
            <ToastContainer
                position="top-right"
                autoClose={3000}
                newestOnTop
                closeOnClick
                pauseOnHover
                limit={3}
            />

            <form
                onSubmit={handleSubmit(onSubmit)}
                className="p-8 md:shadow-xl md:p-16 rounded-xl"
            >
                <h1 className="title mb-4">Sign Up</h1>

                <p>
                    Already have an account?{" "}
                    <span className="text-blue-500">
            <Link to="/login">Login</Link>
          </span>
                </p>

                <div className="flex flex-col gap-4 py-8">
                    {/* Firstname Input */}
                    <div>
                        <label htmlFor="firstname" className="form-label">
                            First name*
                        </label>
                        <input
                            {...register("firstname", {
                                required: "First name is required.",
                                minLength: {
                                    value: NAME_MIN,
                                    message: `First name must contain at least ${NAME_MIN} characters.`,
                                },
                                maxLength: {
                                    value: NAME_MAX,
                                    message: `First name cannot exceed ${NAME_MAX} characters.`,
                                },
                                pattern: {
                                    value: new RegExp(NAME_REGEX),
                                    message: "First name must contain only letters without spaces.",
                                },
                            })}
                            type="text"
                            id="firstname"
                            placeholder="John"
                            className={`form-input-box ${errors.firstname && "error"}`}
                        />
                        {errors.firstname && (
                            <p className="form-error-label">{errors.firstname.message}</p>
                        )}
                    </div>

                    {/* Lastname Input */}
                    <div>
                        <label htmlFor="lastname" className="form-label">
                            Last name*
                        </label>
                        <input
                            {...register("lastname", {
                                required: "Last name is required.",
                                minLength: {
                                    value: NAME_MIN,
                                    message: `Last name must contain at least ${NAME_MIN} characters.`,
                                },
                                maxLength: {
                                    value: NAME_MAX,
                                    message: `Last name cannot exceed ${NAME_MAX} characters.`,
                                },
                                pattern: {
                                    value: new RegExp(NAME_REGEX),
                                    message: "Last name must contain only letters without spaces.",
                                },
                            })}
                            type="text"
                            id="lastname"
                            placeholder="Doe"
                            className={`form-input-box ${errors.lastname && "error"}`}
                        />
                        {errors.lastname && (
                            <p className="form-error-label">{errors.lastname.message}</p>
                        )}
                    </div>

                    {/* Username Input */}
                    <div>
                        <label htmlFor="username" className="form-label">
                            Username*
                        </label>
                        <input
                            {...register("username", {
                                required: "Username is required",
                                minLength: {
                                    value: USERNAME_MIN,
                                    message: `Username must contain at least ${USERNAME_MIN} characters.`,
                                },
                                maxLength: {
                                    value: USERNAME_MAX,
                                    message: `Username cannot exceed ${USERNAME_MAX} characters.`,
                                },
                                pattern: {
                                    value: new RegExp(USERNAME_REGEX),
                                    message:
                                        "Username must start with a letter and can only contain alphanumeric characters without whitespaces.",
                                },
                            })}
                            type="text"
                            id="username"
                            placeholder="johndoe1"
                            className={`form-input-box ${errors.username && "error"}`}
                        />
                        {errors.username && (
                            <p className="form-error-label">{errors.username.message}</p>
                        )}
                    </div>

                    {/* Password Input */}
                    <div>
                        <label htmlFor="password" className="form-label">
                            Password*
                        </label>
                        <input
                            {...register("password", {
                                required: PASSWORD_REQUIRED,
                                minLength: {
                                    value: PASSWORD_MIN,
                                    message: `Password must contain at least ${PASSWORD_MIN} characters.`,
                                },
                                maxLength: {
                                    value: PASSWORD_MAX,
                                    message: `Password cannot exceed ${PASSWORD_MAX} characters.`,
                                },
                                pattern: {
                                    value: new RegExp(PASSWORD_REGEX),
                                    message: "Password cannot contain whitespace.",
                                },
                            })}
                            type="password"
                            id="password"
                            placeholder="********"
                            className={`form-input-box ${errors.password && "error"}`}
                        />
                        {errors.password && (
                            <p className="form-error-label">{errors.password.message}</p>
                        )}
                    </div>

                    {/* Password Confirm Input */}
                    <div>
                        <label htmlFor="password-confirm" className="form-label">
                            Confirm Password*
                        </label>
                        <input
                            {...register("confirmPassword", {
                                required: "Confirming password is required",
                                validate: (value) =>
                                    value === watch("password") || "Passwords do not match.",
                            })}
                            type="password"
                            id="password-confirm"
                            placeholder="********"
                            className={`form-input-box ${errors.confirmPassword && "error"}`}
                        />
                        {errors.confirmPassword && (
                            <p className="form-error-label">{errors.confirmPassword.message}</p>
                        )}
                    </div>
                </div>

                {/* Submit Button */}
                <div className="flex justify-center md:justify-start">
                    <button
                        disabled={loading}
                        type="submit"
                        className={`mt-4 px-6 py-2 rounded-lg transition ${
                            loading
                                ? "bg-neutral-500"
                                : "bg-blue-500 text-white hover:bg-blue-600"
                        }`}
                    >
                        {loading ? "Creating account.." : "Sign Up"}
                    </button>
                </div>
            </form>
        </div>
    );
};

export default RegisterPage;

import { useState } from "react";
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "../../assets/styles/formStyles.css";

const RegisterPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ mode: "onBlur" });

  const [loading, setLoading] = useState(false);

  const onSubmit = async (data) => {
    setLoading(true);

    try {
      console.log(data);
    } catch (error) {
      console.error(error.message);
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
              First name
            </label>
            <input
              {...register("firstname")}
              type="text"
              id="firstname"
              placeholder="John"
              className="form-input-box"
            />
          </div>

          {/* Lastname Input */}
          <div>
            <label htmlFor="lastname" className="form-label">
              Last name
            </label>
            <input
              {...register("lastname")}
              type="text"
              id="lastname"
              placeholder="Doe"
              className="form-input-box"
            />
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
                  value: 2,
                  message: "Username must containt at least 2 charachters",
                },
                maxLength: {
                  value: 20,
                  message: "Username cannot exceed 20 charachter limit",
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
                required: "Password is required",
                minLength: {
                  value: 8,
                  message: "Password must containt at least 8 charachters",
                },
                maxLength: {
                  value: 255,
                  message: "Password cannot exceed 255 charachter limit",
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

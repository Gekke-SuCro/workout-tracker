import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "../assets/styles/formStyles.css";
import { useAuth } from "../contexts/authContext";

const LoginPage = () => {
  const { login } = useAuth();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ mode: "onBlur" });

  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const onSubmit = async (data) => {
    setLoading(true);
    const authToast = toast.loading("Logging in...");

    try {
      await login(data.username, data.password);

      toast.update(authToast, {
        render: "Welcome back! 🎉",
        type: "success",
        isLoading: false,
        closeButton: true,
        autoClose: 3000,
      });

      await new Promise((resolve) => setTimeout(resolve, 1000));
      navigate("/");
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
        <h1 className="title mb-4">Login</h1>

        <p>
          Not a member?{" "}
          <span className="text-blue-500">
            <Link to="/">Register</Link>
          </span>
        </p>

        <div className="flex flex-col gap-4 py-8">
          {/* Username Input */}
          <div>
            <label htmlFor="username" className="form-label">
              Username
            </label>
            <input
              {...register("username", {
                required: "Username is required",
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
              Password
            </label>
            <input
              {...register("password", {
                required: "Password is required",
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
            {loading ? "Logging in..." : "Login"}
          </button>
        </div>
      </form>
    </div>
  );
};

export default LoginPage;

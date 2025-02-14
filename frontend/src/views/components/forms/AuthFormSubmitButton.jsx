import PropTypes from "prop-types";

const AuthSubmitButton = ({ loading, text }) => {
    return (
        <button
            disabled={loading}
            type="submit"
            className={`mt-4 px-6 py-2 rounded-lg transition ${
                loading ? "bg-neutral-500" : "bg-blue-500 text-white hover:bg-blue-600"
            }`}
        >
            {loading ? "Processing..." : text}
        </button>
    );
};

AuthSubmitButton.propTypes = {
    loading: PropTypes.bool.isRequired,
    text: PropTypes.string.isRequired,
}

export default AuthSubmitButton;

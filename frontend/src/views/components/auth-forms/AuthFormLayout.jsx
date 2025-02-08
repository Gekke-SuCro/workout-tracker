import PropTypes from "prop-types";

const AuthFormLayout = ({ title, children, onSubmit }) => {
    return (
        <div className="mx-auto sm:max-w-md flex flex-col grow justify-center min-h-svh">
            <form
                onSubmit={onSubmit}
                className="p-8 md:shadow-xl md:p-16 rounded-xl"
            >
                <h1 className="title mb-4">{title}</h1>
                {children}
            </form>
        </div>
    );
};

AuthFormLayout.propTypes = {
    title: PropTypes.string.isRequired,
    children: PropTypes.node.isRequired,
    onSubmit: PropTypes.func.isRequired,
}

export default AuthFormLayout;

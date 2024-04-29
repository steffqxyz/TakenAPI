package xyz.steffq.takenapi.command;

import org.mineacademy.fo.ReflectionUtil;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.model.Tuple;
import xyz.steffq.takenapi.command.annotation.Command;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AnnotatedCommand extends SimpleCommand {

    private final Map<String, Tuple<Method, Command>> methods = new HashMap<>();

    protected AnnotatedCommand(String label) {
        super(label);

        this.findMethods();
    }

    private void findMethods() {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getDeclaredAnnotation(Command.class);
                int modifiers = method.getModifiers();
                Valid.checkBoolean(!Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers), "Method " + method.getName() + " in class " + this.getClass().getSimpleName() + " must be public and non-static");
                this.methods.put(command.value(), new Tuple<>(method, command));
            }
        }
    }

    @Override
    protected final void onCommand() {
        String param = this.args.length > 0 ? this.args[0] : "";
        Tuple<Method, Command> methodTuple = this.methods.get(param.toLowerCase());

        if (methodTuple != null) {
            Method method = methodTuple.getKey();
            Command command = methodTuple.getValue();

            if (command.requirePlayer() && !this.isPlayer())
                returnTell("This command is only for players.");

            if (this.args.length < command.minArguments() || this.args.length > command.maxArguments())
                returnTell("Usage : /{label} <" + String.join("|", this.methods.keySet()
                        .stream().filter(methodName -> !methodName.isEmpty()).collect(Collectors.toList())) + ">");

            if (!command.permission().isEmpty() && !this.hasPerm(command.permission()))
                returnTell("You do not have permission to use this command.");

            ReflectionUtil.invoke(method, this);

        } else {
            this.tell("Usage : /{label} <" + String.join("|", this.methods.keySet()
                    .stream().filter(methodName -> !methodName.isEmpty()).collect(Collectors.toList())) + ">");
        }
    }

    @Override
    protected final List<String> tabComplete() {

        if (this.args.length == 1)
            return this.completeLastWord(this.methods.keySet());

        return NO_COMPLETE;
    }
}

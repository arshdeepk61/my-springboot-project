package org.example.service;

import org.example.model.Task;
import org.example.model.TaskPriority;
import org.example.model.TaskStatus;
import org.example.model.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TaskBuilder {

private Task currenttask;


    public TaskBuilder() {
        this.currenttask =  new Task();
    }

    public TaskBuilder Withtile(String title)
    {

        this.currenttask.setTitle(title);
        return this;

    }

    public TaskBuilder WithDesc(String Desc)
    {

        this.currenttask.setDescription( Desc);
        return this;

    }
    public TaskBuilder withStatus(TaskStatus TaskStatus)
    {

        this.currenttask.setStatus(TaskStatus);
        return this;

    }
    public TaskBuilder withTaskPriority(TaskPriority taskPriority)
    {

        this.currenttask.setPriority(taskPriority);
        return this;

    }
    public TaskBuilder withUser(User user)
    {

        this.currenttask.setUser(user);
        return this;

    }

    public Task build()
    {
      Task task = this.currenttask;

      this.currenttask= new Task();

      return task;
    }

    public Task getCurrenttask() {
        return currenttask;
    }
}

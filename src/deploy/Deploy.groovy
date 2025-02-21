package deploy

class Deploy {
    public Script steps

    Deploy(Script steps){
        this.steps = steps
    }

    void println() {
        this.steps.println("deply")
    }

}

class DeployZ {
    public Script steps

    DeployZ(Script steps){
        this.steps = steps
    }

    void println() {
        this.steps.println("deplyZ")
        this.steps.lib.stuff(this.steps)
    }

}



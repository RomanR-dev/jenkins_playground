package org

import deploy.Deploy

class Organize extends Deploy {

    Organize(Script steps) {
        super(steps)
    }

    @Override
    void println() {
        steps.println("ORGNIZE")
    }
}

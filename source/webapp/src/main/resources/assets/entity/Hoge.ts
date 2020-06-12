
export class Hoge {
    constructor(private name: string, private age: number, private job: string) {
    }
    getName(): string {
        return this.name;
    }
    getAge(): number {
        return this.age;
    }
    getJob(): string {
        return this.job;
    }
    setJob(job: string) {
        this.job = job;
    }
}
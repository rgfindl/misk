import AstNode from '@misk-console/parsing/ast/AstNode';

export default class Unexpected extends AstNode {
  value: string;

  constructor(value: string) {
    super();
    this.value = value;
  }

  render() {
    return this.value;
  }
}

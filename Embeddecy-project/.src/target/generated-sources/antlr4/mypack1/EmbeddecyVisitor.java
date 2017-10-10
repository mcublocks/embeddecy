// Generated from Embeddecy.g4 by ANTLR 4.6
package mypack1;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EmbeddecyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EmbeddecyVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(EmbeddecyParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#genericSelection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericSelection(EmbeddecyParser.GenericSelectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#genericAssocList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericAssocList(EmbeddecyParser.GenericAssocListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#genericAssociation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericAssociation(EmbeddecyParser.GenericAssociationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpression(EmbeddecyParser.PostfixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#argumentExpressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentExpressionList(EmbeddecyParser.ArgumentExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(EmbeddecyParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#unaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(EmbeddecyParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(EmbeddecyParser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(EmbeddecyParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(EmbeddecyParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#shiftExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpression(EmbeddecyParser.ShiftExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(EmbeddecyParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(EmbeddecyParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(EmbeddecyParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclusiveOrExpression(EmbeddecyParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclusiveOrExpression(EmbeddecyParser.InclusiveOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(EmbeddecyParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(EmbeddecyParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(EmbeddecyParser.ConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(EmbeddecyParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(EmbeddecyParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(EmbeddecyParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(EmbeddecyParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(EmbeddecyParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#declarationSpecifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSpecifiers(EmbeddecyParser.DeclarationSpecifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#declarationSpecifiers2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSpecifiers2(EmbeddecyParser.DeclarationSpecifiers2Context ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#declarationSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSpecifier(EmbeddecyParser.DeclarationSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#initDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitDeclaratorList(EmbeddecyParser.InitDeclaratorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#initDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitDeclarator(EmbeddecyParser.InitDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#storageClassSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorageClassSpecifier(EmbeddecyParser.StorageClassSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#typeSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSpecifier(EmbeddecyParser.TypeSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructOrUnionSpecifier(EmbeddecyParser.StructOrUnionSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#structOrUnion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructOrUnion(EmbeddecyParser.StructOrUnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#structDeclarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclarationList(EmbeddecyParser.StructDeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaration(EmbeddecyParser.StructDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#specifierQualifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecifierQualifierList(EmbeddecyParser.SpecifierQualifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#structDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaratorList(EmbeddecyParser.StructDeclaratorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#structDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclarator(EmbeddecyParser.StructDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#enumSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumSpecifier(EmbeddecyParser.EnumSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#enumeratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumeratorList(EmbeddecyParser.EnumeratorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#enumerator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumerator(EmbeddecyParser.EnumeratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#enumerationConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumerationConstant(EmbeddecyParser.EnumerationConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#atomicTypeSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicTypeSpecifier(EmbeddecyParser.AtomicTypeSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#typeQualifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeQualifier(EmbeddecyParser.TypeQualifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#functionSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionSpecifier(EmbeddecyParser.FunctionSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#alignmentSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlignmentSpecifier(EmbeddecyParser.AlignmentSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarator(EmbeddecyParser.DeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#directDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectDeclarator(EmbeddecyParser.DirectDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#gccDeclaratorExtension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGccDeclaratorExtension(EmbeddecyParser.GccDeclaratorExtensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#gccAttributeSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGccAttributeSpecifier(EmbeddecyParser.GccAttributeSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#gccAttributeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGccAttributeList(EmbeddecyParser.GccAttributeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#gccAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGccAttribute(EmbeddecyParser.GccAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#nestedParenthesesBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedParenthesesBlock(EmbeddecyParser.NestedParenthesesBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#pointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointer(EmbeddecyParser.PointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#typeQualifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeQualifierList(EmbeddecyParser.TypeQualifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#parameterTypeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterTypeList(EmbeddecyParser.ParameterTypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(EmbeddecyParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclaration(EmbeddecyParser.ParameterDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(EmbeddecyParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(EmbeddecyParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#abstractDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstractDeclarator(EmbeddecyParser.AbstractDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectAbstractDeclarator(EmbeddecyParser.DirectAbstractDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#typedefName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedefName(EmbeddecyParser.TypedefNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializer(EmbeddecyParser.InitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#initializerList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializerList(EmbeddecyParser.InitializerListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#designation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesignation(EmbeddecyParser.DesignationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#designatorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesignatorList(EmbeddecyParser.DesignatorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#designator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesignator(EmbeddecyParser.DesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#staticAssertDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaticAssertDeclaration(EmbeddecyParser.StaticAssertDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(EmbeddecyParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#labeledStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabeledStatement(EmbeddecyParser.LabeledStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(EmbeddecyParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#blockItemList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItemList(EmbeddecyParser.BlockItemListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(EmbeddecyParser.BlockItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(EmbeddecyParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(EmbeddecyParser.SelectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(EmbeddecyParser.IterationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatement(EmbeddecyParser.JumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(EmbeddecyParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#translationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationUnit(EmbeddecyParser.TranslationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#externalDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternalDeclaration(EmbeddecyParser.ExternalDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#taskDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaskDefinition(EmbeddecyParser.TaskDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#taskName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaskName(EmbeddecyParser.TaskNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#taskCompoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaskCompoundStatement(EmbeddecyParser.TaskCompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#taskFuncAndVarDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaskFuncAndVarDefinitions(EmbeddecyParser.TaskFuncAndVarDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#taskFuncAndVarDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaskFuncAndVarDefinition(EmbeddecyParser.TaskFuncAndVarDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#packageDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDefinition(EmbeddecyParser.PackageDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#packageName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageName(EmbeddecyParser.PackageNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#packageFuncAndVarDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageFuncAndVarDefinitions(EmbeddecyParser.PackageFuncAndVarDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#packageFuncAndVarDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageFuncAndVarDefinition(EmbeddecyParser.PackageFuncAndVarDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#publicitySpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPublicitySpec(EmbeddecyParser.PublicitySpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(EmbeddecyParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EmbeddecyParser#declarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationList(EmbeddecyParser.DeclarationListContext ctx);
}
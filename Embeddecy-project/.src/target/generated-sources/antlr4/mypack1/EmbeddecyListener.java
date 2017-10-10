// Generated from Embeddecy.g4 by ANTLR 4.6
package mypack1;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EmbeddecyParser}.
 */
public interface EmbeddecyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(EmbeddecyParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(EmbeddecyParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#genericSelection}.
	 * @param ctx the parse tree
	 */
	void enterGenericSelection(EmbeddecyParser.GenericSelectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#genericSelection}.
	 * @param ctx the parse tree
	 */
	void exitGenericSelection(EmbeddecyParser.GenericSelectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#genericAssocList}.
	 * @param ctx the parse tree
	 */
	void enterGenericAssocList(EmbeddecyParser.GenericAssocListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#genericAssocList}.
	 * @param ctx the parse tree
	 */
	void exitGenericAssocList(EmbeddecyParser.GenericAssocListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#genericAssociation}.
	 * @param ctx the parse tree
	 */
	void enterGenericAssociation(EmbeddecyParser.GenericAssociationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#genericAssociation}.
	 * @param ctx the parse tree
	 */
	void exitGenericAssociation(EmbeddecyParser.GenericAssociationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(EmbeddecyParser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(EmbeddecyParser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#argumentExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentExpressionList(EmbeddecyParser.ArgumentExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#argumentExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentExpressionList(EmbeddecyParser.ArgumentExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(EmbeddecyParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(EmbeddecyParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(EmbeddecyParser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(EmbeddecyParser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(EmbeddecyParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(EmbeddecyParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(EmbeddecyParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(EmbeddecyParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(EmbeddecyParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(EmbeddecyParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(EmbeddecyParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(EmbeddecyParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(EmbeddecyParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(EmbeddecyParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(EmbeddecyParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(EmbeddecyParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(EmbeddecyParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(EmbeddecyParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveOrExpression(EmbeddecyParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveOrExpression(EmbeddecyParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveOrExpression(EmbeddecyParser.InclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveOrExpression(EmbeddecyParser.InclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(EmbeddecyParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(EmbeddecyParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(EmbeddecyParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(EmbeddecyParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(EmbeddecyParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(EmbeddecyParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(EmbeddecyParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(EmbeddecyParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(EmbeddecyParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(EmbeddecyParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(EmbeddecyParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(EmbeddecyParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(EmbeddecyParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(EmbeddecyParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(EmbeddecyParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(EmbeddecyParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#declarationSpecifiers}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationSpecifiers(EmbeddecyParser.DeclarationSpecifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#declarationSpecifiers}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationSpecifiers(EmbeddecyParser.DeclarationSpecifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#declarationSpecifiers2}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationSpecifiers2(EmbeddecyParser.DeclarationSpecifiers2Context ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#declarationSpecifiers2}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationSpecifiers2(EmbeddecyParser.DeclarationSpecifiers2Context ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#declarationSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationSpecifier(EmbeddecyParser.DeclarationSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#declarationSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationSpecifier(EmbeddecyParser.DeclarationSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclaratorList(EmbeddecyParser.InitDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclaratorList(EmbeddecyParser.InitDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclarator(EmbeddecyParser.InitDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclarator(EmbeddecyParser.InitDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#storageClassSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStorageClassSpecifier(EmbeddecyParser.StorageClassSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#storageClassSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStorageClassSpecifier(EmbeddecyParser.StorageClassSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpecifier(EmbeddecyParser.TypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpecifier(EmbeddecyParser.TypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStructOrUnionSpecifier(EmbeddecyParser.StructOrUnionSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStructOrUnionSpecifier(EmbeddecyParser.StructOrUnionSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#structOrUnion}.
	 * @param ctx the parse tree
	 */
	void enterStructOrUnion(EmbeddecyParser.StructOrUnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#structOrUnion}.
	 * @param ctx the parse tree
	 */
	void exitStructOrUnion(EmbeddecyParser.StructOrUnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#structDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclarationList(EmbeddecyParser.StructDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#structDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclarationList(EmbeddecyParser.StructDeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaration(EmbeddecyParser.StructDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaration(EmbeddecyParser.StructDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#specifierQualifierList}.
	 * @param ctx the parse tree
	 */
	void enterSpecifierQualifierList(EmbeddecyParser.SpecifierQualifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#specifierQualifierList}.
	 * @param ctx the parse tree
	 */
	void exitSpecifierQualifierList(EmbeddecyParser.SpecifierQualifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#structDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaratorList(EmbeddecyParser.StructDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#structDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaratorList(EmbeddecyParser.StructDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#structDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclarator(EmbeddecyParser.StructDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#structDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclarator(EmbeddecyParser.StructDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#enumSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterEnumSpecifier(EmbeddecyParser.EnumSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#enumSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitEnumSpecifier(EmbeddecyParser.EnumSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#enumeratorList}.
	 * @param ctx the parse tree
	 */
	void enterEnumeratorList(EmbeddecyParser.EnumeratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#enumeratorList}.
	 * @param ctx the parse tree
	 */
	void exitEnumeratorList(EmbeddecyParser.EnumeratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#enumerator}.
	 * @param ctx the parse tree
	 */
	void enterEnumerator(EmbeddecyParser.EnumeratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#enumerator}.
	 * @param ctx the parse tree
	 */
	void exitEnumerator(EmbeddecyParser.EnumeratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#enumerationConstant}.
	 * @param ctx the parse tree
	 */
	void enterEnumerationConstant(EmbeddecyParser.EnumerationConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#enumerationConstant}.
	 * @param ctx the parse tree
	 */
	void exitEnumerationConstant(EmbeddecyParser.EnumerationConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#atomicTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterAtomicTypeSpecifier(EmbeddecyParser.AtomicTypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#atomicTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitAtomicTypeSpecifier(EmbeddecyParser.AtomicTypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#typeQualifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeQualifier(EmbeddecyParser.TypeQualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#typeQualifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeQualifier(EmbeddecyParser.TypeQualifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#functionSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterFunctionSpecifier(EmbeddecyParser.FunctionSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#functionSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitFunctionSpecifier(EmbeddecyParser.FunctionSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#alignmentSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterAlignmentSpecifier(EmbeddecyParser.AlignmentSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#alignmentSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitAlignmentSpecifier(EmbeddecyParser.AlignmentSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(EmbeddecyParser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(EmbeddecyParser.DeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#directDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterDirectDeclarator(EmbeddecyParser.DirectDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#directDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitDirectDeclarator(EmbeddecyParser.DirectDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#gccDeclaratorExtension}.
	 * @param ctx the parse tree
	 */
	void enterGccDeclaratorExtension(EmbeddecyParser.GccDeclaratorExtensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#gccDeclaratorExtension}.
	 * @param ctx the parse tree
	 */
	void exitGccDeclaratorExtension(EmbeddecyParser.GccDeclaratorExtensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#gccAttributeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterGccAttributeSpecifier(EmbeddecyParser.GccAttributeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#gccAttributeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitGccAttributeSpecifier(EmbeddecyParser.GccAttributeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#gccAttributeList}.
	 * @param ctx the parse tree
	 */
	void enterGccAttributeList(EmbeddecyParser.GccAttributeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#gccAttributeList}.
	 * @param ctx the parse tree
	 */
	void exitGccAttributeList(EmbeddecyParser.GccAttributeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#gccAttribute}.
	 * @param ctx the parse tree
	 */
	void enterGccAttribute(EmbeddecyParser.GccAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#gccAttribute}.
	 * @param ctx the parse tree
	 */
	void exitGccAttribute(EmbeddecyParser.GccAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#nestedParenthesesBlock}.
	 * @param ctx the parse tree
	 */
	void enterNestedParenthesesBlock(EmbeddecyParser.NestedParenthesesBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#nestedParenthesesBlock}.
	 * @param ctx the parse tree
	 */
	void exitNestedParenthesesBlock(EmbeddecyParser.NestedParenthesesBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#pointer}.
	 * @param ctx the parse tree
	 */
	void enterPointer(EmbeddecyParser.PointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#pointer}.
	 * @param ctx the parse tree
	 */
	void exitPointer(EmbeddecyParser.PointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#typeQualifierList}.
	 * @param ctx the parse tree
	 */
	void enterTypeQualifierList(EmbeddecyParser.TypeQualifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#typeQualifierList}.
	 * @param ctx the parse tree
	 */
	void exitTypeQualifierList(EmbeddecyParser.TypeQualifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#parameterTypeList}.
	 * @param ctx the parse tree
	 */
	void enterParameterTypeList(EmbeddecyParser.ParameterTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#parameterTypeList}.
	 * @param ctx the parse tree
	 */
	void exitParameterTypeList(EmbeddecyParser.ParameterTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(EmbeddecyParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(EmbeddecyParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration(EmbeddecyParser.ParameterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration(EmbeddecyParser.ParameterDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(EmbeddecyParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(EmbeddecyParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(EmbeddecyParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(EmbeddecyParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#abstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterAbstractDeclarator(EmbeddecyParser.AbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#abstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitAbstractDeclarator(EmbeddecyParser.AbstractDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterDirectAbstractDeclarator(EmbeddecyParser.DirectAbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitDirectAbstractDeclarator(EmbeddecyParser.DirectAbstractDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#typedefName}.
	 * @param ctx the parse tree
	 */
	void enterTypedefName(EmbeddecyParser.TypedefNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#typedefName}.
	 * @param ctx the parse tree
	 */
	void exitTypedefName(EmbeddecyParser.TypedefNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(EmbeddecyParser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(EmbeddecyParser.InitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#initializerList}.
	 * @param ctx the parse tree
	 */
	void enterInitializerList(EmbeddecyParser.InitializerListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#initializerList}.
	 * @param ctx the parse tree
	 */
	void exitInitializerList(EmbeddecyParser.InitializerListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#designation}.
	 * @param ctx the parse tree
	 */
	void enterDesignation(EmbeddecyParser.DesignationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#designation}.
	 * @param ctx the parse tree
	 */
	void exitDesignation(EmbeddecyParser.DesignationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#designatorList}.
	 * @param ctx the parse tree
	 */
	void enterDesignatorList(EmbeddecyParser.DesignatorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#designatorList}.
	 * @param ctx the parse tree
	 */
	void exitDesignatorList(EmbeddecyParser.DesignatorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#designator}.
	 * @param ctx the parse tree
	 */
	void enterDesignator(EmbeddecyParser.DesignatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#designator}.
	 * @param ctx the parse tree
	 */
	void exitDesignator(EmbeddecyParser.DesignatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#staticAssertDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStaticAssertDeclaration(EmbeddecyParser.StaticAssertDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#staticAssertDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStaticAssertDeclaration(EmbeddecyParser.StaticAssertDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(EmbeddecyParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(EmbeddecyParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledStatement(EmbeddecyParser.LabeledStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledStatement(EmbeddecyParser.LabeledStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(EmbeddecyParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(EmbeddecyParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#blockItemList}.
	 * @param ctx the parse tree
	 */
	void enterBlockItemList(EmbeddecyParser.BlockItemListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#blockItemList}.
	 * @param ctx the parse tree
	 */
	void exitBlockItemList(EmbeddecyParser.BlockItemListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void enterBlockItem(EmbeddecyParser.BlockItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void exitBlockItem(EmbeddecyParser.BlockItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(EmbeddecyParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(EmbeddecyParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(EmbeddecyParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(EmbeddecyParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(EmbeddecyParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(EmbeddecyParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(EmbeddecyParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(EmbeddecyParser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(EmbeddecyParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(EmbeddecyParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void enterTranslationUnit(EmbeddecyParser.TranslationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void exitTranslationUnit(EmbeddecyParser.TranslationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExternalDeclaration(EmbeddecyParser.ExternalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExternalDeclaration(EmbeddecyParser.ExternalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#taskDefinition}.
	 * @param ctx the parse tree
	 */
	void enterTaskDefinition(EmbeddecyParser.TaskDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#taskDefinition}.
	 * @param ctx the parse tree
	 */
	void exitTaskDefinition(EmbeddecyParser.TaskDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#taskName}.
	 * @param ctx the parse tree
	 */
	void enterTaskName(EmbeddecyParser.TaskNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#taskName}.
	 * @param ctx the parse tree
	 */
	void exitTaskName(EmbeddecyParser.TaskNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#taskCompoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterTaskCompoundStatement(EmbeddecyParser.TaskCompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#taskCompoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitTaskCompoundStatement(EmbeddecyParser.TaskCompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#taskFuncAndVarDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterTaskFuncAndVarDefinitions(EmbeddecyParser.TaskFuncAndVarDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#taskFuncAndVarDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitTaskFuncAndVarDefinitions(EmbeddecyParser.TaskFuncAndVarDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#taskFuncAndVarDefinition}.
	 * @param ctx the parse tree
	 */
	void enterTaskFuncAndVarDefinition(EmbeddecyParser.TaskFuncAndVarDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#taskFuncAndVarDefinition}.
	 * @param ctx the parse tree
	 */
	void exitTaskFuncAndVarDefinition(EmbeddecyParser.TaskFuncAndVarDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#packageDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPackageDefinition(EmbeddecyParser.PackageDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#packageDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPackageDefinition(EmbeddecyParser.PackageDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#packageName}.
	 * @param ctx the parse tree
	 */
	void enterPackageName(EmbeddecyParser.PackageNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#packageName}.
	 * @param ctx the parse tree
	 */
	void exitPackageName(EmbeddecyParser.PackageNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#packageFuncAndVarDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterPackageFuncAndVarDefinitions(EmbeddecyParser.PackageFuncAndVarDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#packageFuncAndVarDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitPackageFuncAndVarDefinitions(EmbeddecyParser.PackageFuncAndVarDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#packageFuncAndVarDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPackageFuncAndVarDefinition(EmbeddecyParser.PackageFuncAndVarDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#packageFuncAndVarDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPackageFuncAndVarDefinition(EmbeddecyParser.PackageFuncAndVarDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#publicitySpec}.
	 * @param ctx the parse tree
	 */
	void enterPublicitySpec(EmbeddecyParser.PublicitySpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#publicitySpec}.
	 * @param ctx the parse tree
	 */
	void exitPublicitySpec(EmbeddecyParser.PublicitySpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(EmbeddecyParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(EmbeddecyParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EmbeddecyParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationList(EmbeddecyParser.DeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EmbeddecyParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationList(EmbeddecyParser.DeclarationListContext ctx);
}
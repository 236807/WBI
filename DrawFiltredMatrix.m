function [] = DrawFiltredMatrix(filtredMatrix)
sizeMatrix=size(filtredMatrix);
%figure
spy(filtredMatrix);
title('Filtred matrix by Dotplot');
xlabel('1-sequence');
ylabel('2-sequence');
axis([1 sizeMatrix(1) + 1 1 sizeMatrix(2) + 1])
saveas(gcf,'plot.png');
end
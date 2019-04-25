function [filtredMatrix]=filterMatrix(matrix,window,error)
sizeMatrix = size(matrix);
filtredMatrix = zeros ( sizeMatrix(1),sizeMatrix(2));
for x=1:sizeMatrix(1) - window 
    for y = 1:sizeMatrix(2) - window 
        m = x;
        n = y;
        correct = 0;
            for c = 1:window
                if matrix(m,n) == 1
                    correct = correct + 1;
                end
                m = m + 1;
                n = n + 1;
            end
            if correct >= (window - error)
                m = x;
                n = y;
                    for c = 1:window
                        if matrix(m,n) == 1
                            filtredMatrix(m,n) = 1;
                        end
                    end
                    m = m + 1;
                    n = n + 1;
            end
    end
end
end